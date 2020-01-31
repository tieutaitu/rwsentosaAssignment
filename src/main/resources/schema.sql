DROP TABLE IF EXISTS currency_symbol;
  
CREATE TABLE currency_symbol (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  symbol VARCHAR(3) NOT NULL,
  description VARCHAR(100),
  rounding_number INT NOT NULL
);

DROP TABLE IF EXISTS currency_exchange_rate;
  
CREATE TABLE currency_exchange_rate (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  currency_from BIGINT NOT NULL,
  currency_to BIGINT NOT NULL,
  exchange_rate DOUBLE NOT NULL,
  foreign key (currency_from) references currency_symbol(id),
  foreign key (currency_to) references currency_symbol(id)
);

DROP TABLE IF EXISTS currency_exchange_history;

CREATE TABLE currency_exchange_history (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  customer_name VARCHAR(100),
  currency_from BIGINT NOT NULL,
  currency_to BIGINT NOT NULL,
  exchange_rate DOUBLE NOT NULL,
  amount_from DOUBLE NOT NULL,
  amount_to DOUBLE NOT NULL,
  transaction_time TIMESTAMP NOT NULL,
  foreign key (currency_from) references currency_symbol(id),
  foreign key (currency_to) references currency_symbol(id)
);
