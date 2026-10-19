package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MasterConnectionDAO {
    private static final String url = "jdbc:sqlite:data/usuarios.db";

    // ao usar conn é bom usar try para que ele abra a feche a conexão com o banco automaticamente
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url);

        String sql = """


                    CREATE TABLE IF NOT EXISTS clients(
                        id_client INTEGER PRIMARY KEY AUTOINCREMENT,
                        name_client TEXT NOT NULL,
                        phone_client TEXT,
                        adress_client TEXT,
                        status_client TEXT check (status_client in ('PADRAO', 'BOM', 'RUIM')) COLLATE NOCASE
                    );

                    CREATE TABLE IF NOT EXISTS products(
                        id_product INTEGER PRIMARY KEY AUTOINCREMENT,
                        name_product TEXT,
                        code_product TEXT,
                        status_product TEXT,
                        line_product TEXT,
                        description_product TEXT
                    );

                    CREATE TABLE IF NOT EXISTS lot(
                        id_lot INTEGER PRIMARY KEY AUTOINCREMENT,
                        id_product INT,
                        expired_date TEXT,
                        purchas_date TEXT,
                        qnt_lot INTEGER CHECK (qnt_lot >= 0),
                        status_lot TEXT CHECK(status_lot IN ('DISPONIVEL', 'INDISPONIVEL', 'VENCIDO', 'PROMOCIONAL')) COLLATE NOCASE,

                        FOREIGN KEY (id_product) REFERENCES products(id_product)
                    );

                    CREATE TABLE IF NOT EXISTS sales(
                        id_sale INTEGER PRIMARY KEY AUTOINCREMENT,
                      	id_client INTEGER,
                        date_sale TEXT NOT NULL,
                        total_sale REAL

                    );

                    CREATE TABLE IF NOT EXISTS clients_sales(
                        id_client INTEGER,
                        id_sale INTEGER,

                        FOREIGN KEY (id_client) REFERENCES clients(id_client),
                        FOREIGN KEY (id_sale) REFERENCES sales(id_sale),
                        PRIMARY KEY (id_client, id_sale)
                    );

                    CREATE TABLE IF NOT EXISTS products_sales(
                        id_product INTEGER,
                        id_sale INTEGER,

                        FOREIGN KEY (id_product) REFERENCES products(id_product),
                        FOREIGN KEY (id_sale) REFERENCES sales(id_sale),
                        PRIMARY KEY (id_product, id_sale)
                    );

                    CREATE TABLE IF NOT EXISTS payments(
                        id_payment INTEGER PRIMARY KEY AUTOINCREMENT,
                        id_sale INTEGER,
                        status_payment INTEGER CHECK(status_payment == 1 OR status_payment == 0),
                        qnt_installments_payment INTEGER CHECK(qnt_installments_payment > 0),
                        type_payment TEXT,

                        FOREIGN KEY (id_sale) REFERENCES sales(id_sale)
                    );

                    CREATE TABLE IF NOT EXISTS installments(
                        id_installment INTEGER PRIMARY KEY AUTOINCREMENT,
                        id_payment INTEGER,
                        value_installment REAL,
                        value_payed_installment REAL,
                        status_installment TEXT CHECK(status_installment IN ('NAO_PAGO', 'PAGO', 'ATRASADO')) COLLATE NOCASE,

                      	FOREIGN KEY (id_payment) REFERENCES payments(id_payment)

                    );
        """;
        conn.createStatement().execute(sql);
        return conn;
    }

}
