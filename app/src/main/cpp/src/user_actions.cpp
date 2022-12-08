#include <user_actions.h>
#include <charconv>

std::ostream& operator << (std::ostream& os, const std::vector<std::vector<std::string>>& result) {
    for (const auto& row : result) {
        for (const auto& elem : row) {
            os << elem << ' ';
        }
        os << std::endl;
    }
    return os;
}

bool can_sell(const sqlite3_database& db, std::size_t count) {
    auto remainders = db.select(query_solds_and_remainder);
    std::size_t remainder = 0;
    for (const auto& row: remainders) {
        int rem = 0;
        std::from_chars(row[2].c_str(), row[2].c_str() + row[2].size(), rem);
        remainder += rem;
    }

    return count <= remainder;
}