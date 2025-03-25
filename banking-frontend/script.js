const apiBaseUrl = "http://localhost:8080/api/v1"; // Base API URL

// Function to open a new account
function openAccount() {
    const customerID = document.getElementById("customerID").value.trim();
    const initialCredit = document.getElementById("initialCredit").value.trim();
    const messageElement = document.getElementById("accountMessage");

    if (!customerID) {
        messageElement.innerHTML = "❌ Customer ID is required.";
        messageElement.style.color = "red";
        return;
    }

    fetch(`${apiBaseUrl}/open-account?customerID=${customerID}&initialCredit=${initialCredit}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic dXNlcjpwYXNzd29yZA==", // Base64 encoded "user:password"
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Failed to create account");
        }
        return response.json();
    })
    .then(() => {
        messageElement.innerHTML = "✅ Account created successfully!";
        messageElement.style.color = "green";
    })
    .catch(() => {
        messageElement.innerHTML = "❌ Error creating account.";
        messageElement.style.color = "red";
    });
}

// Function to get customer details
function getCustomerDetails() {
    const customerID = document.getElementById("customerSearchID").value.trim();
    const detailsDiv = document.getElementById("customerDetails");

    if (!customerID) {
        detailsDiv.innerHTML = "<p style='color:red;'>❌ Please enter a customer ID.</p>";
        return;
    }

    fetch(`${apiBaseUrl}/customer/${customerID}`, {
        method: "GET",
        headers: {
            "Authorization": "Basic dXNlcjpwYXNzd29yZA==", // Base64 encoded "user:password"
        },
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Customer not found");
        }
        return response.json();
    })
    .then(data => {
        const transactionsHTML = data.transactions;
        detailsDiv.innerHTML = `
            <h3>${data.firstName} ${data.lastName}</h3>
            <p><strong>Balance:</strong> $${data.balance}</p>
            <h4>Transactions:</h4>
            <ul>${transactionsHTML}</ul>
        `;
    })
    .catch(() => {
        detailsDiv.innerHTML = "<p style='color:red;'>❌ Customer not found.</p>";
    });
}
