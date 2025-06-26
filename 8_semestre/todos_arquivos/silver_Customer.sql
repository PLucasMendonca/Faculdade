CREATE TABLE silver_Customer AS 
SELECT 
    CustomerId, 
    UPPER(SUBSTR(FirstName, 1, 1)) || LOWER(SUBSTR(FirstName, 2)) AS FirstName,
    UPPER(SUBSTR(LastName, 1, 1)) || LOWER(SUBSTR(LastName, 2)) AS LastName,
    Company,
    Address,
    City,
    State,
    Country,
    REPLACE(REPLACE(PostalCode, '-', ''), ' ', '') AS PostalCode,
    REPLACE(REPLACE(REPLACE(REPLACE(Phone, '(', ''), ')', ''), '-', ''), ' ', '') AS Phone,
    Fax,
    LOWER(Email) AS Email,
    SupportRepId
FROM bronze_Customer;