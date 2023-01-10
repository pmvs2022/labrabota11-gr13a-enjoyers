#include <authentication.h>

std::vector<std::vector<std::string>> find_user_by_email(const sqlite3_database& db, const std::string& email) {
    const std::string find_by_email = "SELECT user_id, user_email, user_password FROM users WHERE user_email == \"" + email + "\";";
    return db.select(find_by_email);
}

bool log_in(const sqlite3_database &db, std::string_view email, std::string_view password) {
    auto res = find_user_by_email(db, email.data());
    return !res.empty() && aes.encrypt(password) == res[0][2];
}

bool sign_up(const sqlite3_database& db, std::string_view email, std::string_view password) {
    auto email_check = [&db](const std::string& email) {
        return std::regex_match(email, email_regex) && find_user_by_email(db, email).empty();
    };
    auto password_check = [](const std::string& pass) {return std::regex_match(pass, password_regex);};

    if (!email_check(email.data()) || !password_check(password.data())) {
        return false;
    }

    db.param_insert(new_user_data_insert, {email.data(), aes.encrypt(password)});

    return true;
}

bool authenticate_user(int option, std::string_view email, std::string_view password) {
    sqlite3_database users_data(music_saloon_path);
    return option == 0 ? log_in(users_data, email, password) : sign_up(users_data, email, password);
}
