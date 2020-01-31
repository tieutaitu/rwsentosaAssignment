INSERT INTO currency_symbol (id, symbol, description, rounding_number) VALUES
  (1, 'SGD', 'Singapore dollar', 1),
  (2, 'USD', 'US dollar', 2);

  INSERT INTO currency_exchange_rate (id, currency_from, currency_to, exchange_rate) VALUES
  (1, 1, 2, 0.742164),
  (2, 2, 1, 1.34782);
