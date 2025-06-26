CREATE TABLE gold_TopClientes AS
SELECT 
    c.FirstName || ' ' || c.LastName AS Nome,
    c.Email,
    c.Country AS Pais,
    COUNT(i.InvoiceId) AS TotalCompras,
    ROUND(SUM(i.Total), 2) AS ValorTotalGasto,
    ROUND(AVG(i.Total), 2) AS ValorMedioPorCompra,
    MAX(i.InvoiceDate) AS DataUltimaCompra,
    CASE 
        WHEN SUM(i.Total) > 40 THEN 'High Value'
        WHEN SUM(i.Total) > 20 THEN 'Medium Value'
        ELSE 'Low Value'
    END AS ClassificacaoCliente
FROM silver_Customer c
JOIN silver_Invoice i ON c.CustomerId = i.CustomerId
GROUP BY c.CustomerId
ORDER BY ValorTotalGasto DESC;