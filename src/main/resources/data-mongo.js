db = db.getSiblingDB('banking-db');

// Function to get today's and yesterday's dates
function getDates() {
    const today = new Date();
    const yesterday = new Date(today);
    yesterday.setDate(today.getDate() - 1);
    return { today, yesterday };
}

const dates = getDates();

// Insert customers into the database
db.customers.insertMany([
    {
        _id: "123e4567-e89b-12d3-a456-426614174000", // String IDs
        first_name: "John",
        last_name: "Doe"
    },
    {
        _id: "123e4567-e89b-12d3-a456-426614174001", // String IDs
        first_name: "Jane",
        last_name: "Smith"
    }
]);

// Insert accounts linked to customers
db.accounts.insertMany([
    {
        _id: "223e4567-e89b-12d3-a456-426614174000", // String IDs
        customerID: "123e4567-e89b-12d3-a456-426614174000",
        balance: 1000.00
    },
    {
        _id: "223e4567-e89b-12d3-a456-426614174001", // String IDs
        customerID: "123e4567-e89b-12d3-a456-426614174001",
        balance: 500.00
    }
]);

// Insert transactions related to the accounts
db.transactions.insertMany([
    {
        _id: "323e4567-e89b-12d3-a456-426614174000", // String IDs
        accountID: "223e4567-e89b-12d3-a456-426614174000",
        amount: 1000.00,
        timestamp: dates.today
    },
    {
        _id: "323e4567-e89b-12d3-a456-426614174001", // String IDs
        accountID: "223e4567-e89b-12d3-a456-426614174001",
        amount: 500.00,
        timestamp: dates.yesterday
    }
]);

db.customers.updateMany(
    { first_name: { $exists: false } },
    { $set: { first_name: "Unknown" } }
);

db.customers.updateMany(
    { last_name: { $exists: false } },
    { $set: { last_name: "Unknown" } }
);
