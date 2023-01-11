#pragma once

#include <iostream>
#include <sqlite3_database.h>

const std::string music_saloon_path = "/sdcard/Download/Music_Salon3.0.db";
const std::string query_solds_and_remainder = "SELECT DISTINCT CD.compact_code, sum(copies_amount)as sold_amount, CD_amount\n"
                                              "FROM trades\n"
                                              "INNER JOIN storage ON storage.id_storageSlot=trades.id_storageSlot\n"
                                              "INNER JOIN CD ON CD.id_cd=storage.id_cd\n"
                                              "WHERE id_operation = 1\n"
                                              "GROUP by trades.id_storageSlot\n"
                                              "ORDER BY (sold_amount - CD_amount) DESC";

const std::string query_cd_with_max_solds = "SELECT tbl_1.*,\n"
                                            "GROUP_CONCAT(\"Composition: \"\n"
                                            "||coalesce(tbl_2.composition_name,'Unknown')\n"
                                            "||char(10)\n"
                                            "||\"Author: \"\n"
                                            "||coalesce(tbl_2.author_name,'Unknown'),char(10)) as compositions_info\n"
                                            "\n"
                                            "FROM (SELECT  storage.id_cd,\n"
                                            "(\n"
                                            "SELECT max(copies) from (Select sum(copies_amount)as copies,id_cd \n"
                                            "FROM trades\n"
                                            "INNER JOIN storage  ON storage.id_storageSlot=trades.id_storageSlot\n"
                                            "WHERE id_operation=1 \n"
                                            " GROUP BY storage.id_storageSlot)) \n"
                                            " as max_copies_sold,\n"
                                            " group_concat(id_trade) as id_trades,\n"
                                            "compact_code,\n"
                                            "compact_date,\n"
                                            "compact_price,\n"
                                            "company_name,\n"
                                            "performer_name\n"
                                            "\n"
                                            "FROM trades\n"
                                            "\n"
                                            "INNER JOIN storage  ON storage.id_storageSlot=trades.id_storageSlot\n"
                                            "INNER JOIN CD ON CD.id_cd=storage.id_cd\n"
                                            "INNER JOIN companies ON CD.id_company=companies.id_company\n"
                                            "INNER JOIN performers ON CD.id_performer=performers.id_performer\n"
                                            "\n"
                                            "WHERE id_operation=1 \n"
                                            "\n"
                                            "GROUP BY storage.id_storageSlot\n"
                                            "HAVING sum(copies_amount) = max_copies_sold\n"
                                            "\t\t\t\t\t\n"
                                            "ORDER by storage.id_cd) \n"
                                            "as tbl_1\n"
                                            "\n"
                                            " LEFT JOIN (SELECT id_cd,composition_name,author_name \n"
                                            "from compositions \n"
                                            "INNER JOIN CD_compositions USING(id_composition)\n"
                                            "INNER JOIN authors USING(id_author)\n"
                                            ")as tbl_2 ON tbl_2.id_cd=tbl_1.id_cd\n"
                                            "\n"
                                            "GROUp by tbl_1.id_cd";

const std::string query_max_sold_for_the_period = "SELECT  \n"
                                                  "compact_code,\n"
                                                  "sum(copies_amount) as copies_sold ,\n"
                                                  " sum(copies_amount)*replace(compact_price,' ','') as priceOfCDs\n"
                                                  "FROM trades \n"
                                                  "INNER JOIN storage ON trades.id_storageSlot=storage.id_storageSlot\n"
                                                  "INNER JOIN CD on CD.id_cd=storage.id_cd\n"
                                                  " WHERE id_operation=1 \n"
                                                  " AND trade_date BETWEEN '{}' and '{}' /*dd-mm-yyyy */\n"
                                                  " AND trades.id_storageSlot=(SELECT id_cd from CD where compact_code='29830591-7')";

const std::string query_max_cds_sold_with_most_popular_performer = "SELECT \n"
                                                                   "\tsum(copies_amount) as disks_sold,performer_name\n"
                                                                   "From \n"
                                                                   "\tCD\n"
                                                                   "INNER JOIN trades On CD.id_cd = trades.id_storageSlot\n"
                                                                   "INNER JOIN performers USING(id_performer)\n"
                                                                   "WHERE \n"
                                                                   "id_operation=1 \n"
                                                                   "AND id_performer=\n"
                                                                   "\t\t\t(SELECT\t\n"
                                                                   "\t\t\t\tid_performer\n"
                                                                   "\t\t\tFROM\n"
                                                                   "\t\t\t\tCD\n"
                                                                   "\t\t\tGROUP by \n"
                                                                   "\t\t\t\tid_performer\n"
                                                                   "\t\t\tORDER By \n"
                                                                   "\t\t\t\tcount(id_performer) DESC\n"
                                                                   "\t\t\tLIMIT 1)";

const std::string query_out_sum_by_authors = "SELECT \n"
                                             "\tauthors.id_author, \n"
                                             "\tauthor_name,\n"
                                             "\tsum(copies_sold) as copies_sold,\n"
                                             "\tsum(out_compact_sum) as out_compact_sum\n"
                                             "\tFROM \n"
                                             "\tauthors \n"
                                             "INNER JOIN compositions ON authors.id_author=compositions.id_author\n"
                                             "INNER JOIN CD_compositions ON CD_compositions.id_composition=compositions.id_composition\n"
                                             "INNER JOIN (\n"
                                             "SELECT CD.id_cd,\n"
                                             "\tsum(copies_amount) as copies_sold,\n"
                                             "\t(sum(copies_amount)*replace(compact_price,' ','')) as out_compact_sum\n"
                                             "\tFROM trades\n"
                                             "\tINNER JOIN storage ON trades.id_storageSlot=storage.id_storageSlot\n"
                                             "\tINNER JOIN CD ON storage.id_cd=CD.id_cd\n"
                                             "\tWHERE id_operation=1\n"
                                             "\tGROUP BY storage.id_storageSlot)\n"
                                             "\tas tbl_1 ON tbl_1.id_cd=CD_compositions.id_cd \n"
                                             "GROUP by authors.id_author\n"
                                             "\tORDER by authors.id_author";

const std::string query_received_and_sales_cds_by_date = "SELECT id_cd,\n"
                                                        "coalesce(sum(sale_count),0) as sale_count,\n"
                                                        "coalesce(sum(admission_count),0) as admission_count from\n"
                                                        "(SELECT  \n"
                                                        "id_storageSlot as id_cd,\n"
                                                        "CASE \n"
                                                        "\tWHEN id_operation=1\n"
                                                        "\tthen sum(copies_amount)\n"
                                                        "\tEND sale_count,\n"
                                                        "CASE \n"
                                                        "\tWHEN id_operation=2\n"
                                                        "\tthen sum(copies_amount)\n"
                                                        "\tEND admission_count\n"
                                                        "FROM trades \n"
                                                        "WHERE trade_date BETWEEN '{}' and '{}' \n"
                                                        "GROup by id_storageSlot,id_operation)\n"
                                                        "GROUP by id_cd";

std::ostream& operator << (std::ostream& os, const std::vector<std::vector<std::string>>& result);

bool can_sell(const sqlite3_database& db, std::size_t count);

template <typename... Args>
std::vector<std::vector<std::string>> db_query(int option, Args&&... args) {
    sqlite3_database db(music_saloon_path);

    switch (option) {
        case 0:
            return db.select(query_solds_and_remainder);
        case 1:
            return db.param_select(query_max_sold_for_the_period, std::forward<Args>(args)...);
        case 2:
            return db.select(query_cd_with_max_solds);
        case 3:
            return db.select(query_max_cds_sold_with_most_popular_performer);
        case 4:
            return db.select(query_out_sum_by_authors);
        case 5:
            return db.param_select(query_received_and_sales_cds_by_date, std::forward<Args>(args)...);
        default:
            return {};
    }
}
