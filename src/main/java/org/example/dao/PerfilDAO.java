package org.example.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PerfilDAO {
    private static String CaminhoBanco(String username) {
        return "jdbc:sqlite:data/perfis/" + username + ".db";
    }

    public static void criarBanco(String username) throws SQLException{
        File pasta = new File("data/perfis");
        if(!pasta.exists()) pasta.mkdirs(); // Se a pasta nao existir, criamos uma

        try (Connection conn = DriverManager.getConnection(CaminhoBanco(username))){ // procura o driver do banco para se conectar na sessão do usuário
            // Por padrão o comando stmt.execute(sql) executa apenas a PRIMEIRA declaração de querie
            // e ele para a execução quando encontra o primeiro ; e ignora o resto
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
            // Para lidar com o problema, separamos cada querie em um Array
            String[] queries = sql.split(";");

            // usar stmt é excelente ao lidar com varias queries ao mesmo tempo
            // pois reutilizamos o mesmo objeto para cada querie, diferente de usarmos conn
            try (java.sql.Statement stmt = conn.createStatement()){
                for (String query : queries){
                    if(!query.trim().isEmpty()){stmt.execute(query.trim());}
                }
            }

            String bd_config = "PRAGMA foreign_keys = ON";
            conn.createStatement().execute(bd_config);
        }
    }

    public static Connection getConexaoUsuario(String username) throws SQLException{
        return DriverManager.getConnection(CaminhoBanco(username));
    }
}
