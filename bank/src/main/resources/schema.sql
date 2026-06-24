CREATE TABLE IF NOT EXISTS accounts(
    account_id SERIAL PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    holder_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    balance NUMERIC(15,2) DEFAULT 0
);