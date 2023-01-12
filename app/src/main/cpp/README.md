#  C++ backend

1. `fmt` - third-party library for formatting by Victor Zverovich
2. `include/MD5.h` - hashing with MD5 algorithm
3. `include/{aes_table.h,aes128.h}` - encryption with AES-128 cipher
4. `include/sqlite3.h` - C API for sqlite3 databases access
5. `include/sqlite3_database.h` - C++ wrapper over sqlite3 C API
6. `include/authentication.h` - authentication implementation. Emails and passwords are stored in sqlite3 database. Passwords encrypted with AES-128 cipher.
7. `include/user_actions.h` - user's actions with sqlite3 database.
8. `src/*` - source files
9. `native-lib.cpp` - Java interface for C++ native code