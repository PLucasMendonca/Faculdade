CREATE TABLE silver_InvoiceLine AS
SELECT 
    InvoiceLineId,
    InvoiceId,
    TrackId,
    ROUND(UnitPrice, 2) AS UnitPrice,
    Quantity,
    ROUND(UnitPrice * Quantity, 2) AS TotalItem
FROM InvoiceLine;
