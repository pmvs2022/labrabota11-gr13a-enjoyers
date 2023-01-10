#pragma once

#include <iostream>
#include <regex>
#include <user_actions.h>
#include <aes128.h>
#include <sqlite3_database.h>

const cipher::aes128 aes("enjoyers");

const std::regex email_regex("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)"
                             "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]"
                             "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9]"
                             "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\"
                             "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]"
                             "?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\"
                             "x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

const std::regex password_regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

const std::string new_user_data_insert = "INSERT INTO users (user_email, user_password) VALUES (?, ?);";

std::vector<std::vector<std::string>> find_user_by_email(const sqlite3_database& db, const std::string& email);
bool log_in(const sqlite3_database &db, std::string_view email, std::string_view password);
bool sign_up(const sqlite3_database& db, std::string_view email, std::string_view password);
bool authenticate_user(int option, std::string_view email, std::string_view password);
