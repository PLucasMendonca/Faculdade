CREATE TABLE silver_Invoice AS
SELECT 
    InvoiceId,
    CustomerId,
    InvoiceDate,
    UPPER(SUBSTR(BillingAddress, 1, 1)) || LOWER(SUBSTR(BillingAddress, 2)) AS BillingAddress,
    UPPER(SUBSTR(BillingCity, 1, 1)) || LOWER(SUBSTR(BillingCity, 2)) AS BillingCity,
    UPPER(BillingState) AS BillingState,
    BillingCountry,
    REPLACE(REPLACE(BillingPostalCode, '-', ''), ' ', '') AS BillingPostalCode,
    ROUND(Total, 2) AS Total
FROM Invoice;