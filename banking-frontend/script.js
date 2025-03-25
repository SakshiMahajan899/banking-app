const apiBaseUrl = "http://application:8080/api";  // Backend API URL in Docker
// Encode username and password for Basic Authentication
const username = "user";
const password = "password";
const authHeader = "Basic " + btoa(`${username}:${password}`);


// Open Account Function
function openAccount() {
    const customerID = document.getElementById("customerID").value;
    const initialCredit = document.getElementById("initialCredit").value;
    const messageElement = document.getElementById("accountMessage");

    if (!customerID) {
        messageElement.innerHTML = "Customer ID is required.";
        return;
    }

    fetch(`${apiBaseUrl}/open-account?customerID=${customerID}&initialCredit=${initialCredit}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" , "Authorization": authHeader  // Include Basic Auth}
    })
    .then(response => response.json())
    .then(data => {
        messageElement.innerHTML = "✅ Account created successfully!";
        messageElement.style.color = "green";
    })
    .catch(error => {
        messageElement.innerHTML = "❌ Error creating account.";
        messageElement.style.color = "red";
    });
}

// Get Customer Details Function
function getCustomerDetails() {
    const customerID = document.getElementById("customerSearchID").value;
    const detailsDiv = document.getElementById("customerDetails");

    if (!customerID) {
        detailsDiv.innerHTML = "<p style='color:red;'>Please enter a customer ID.</p>";
        return;
    }

    fetch(`${apiBaseUrl}/customer/${customerID}`,{
     "Authorization": authHeader  // Include Basic Auth
    })
    }
    .then(response => response.json())
    .then(data => {
        detailsDiv.innerHTML = `
            <h3>${data.first_name} ${data.last_name}</h3>
            <p><strong>Balance:</strong> $${data.balance}</p>
            <h4>Transactions:</h4>
            <ul>
                ${data.transactions.map(txn => `<li>$${txn.amount} - ${txn.timestamp}</li>`).join("")}
            </ul>
        `;
    })
    .catch(error => {
        detailsDiv.innerHTML = "<p style='color:red;'>Customer not found.</p>";
    });
}

// Tab Navigation
function openTab(tabName) {
    document.querySelectorAll('.tab-content').forEach(tab => {
        tab.classList.remove('active-tab');
    });
    document.getElementById(tabName).classList.add('active-tab');

    document.querySelectorAll('.tab-button').forEach(button => {
        button.classList.remove('active');
    });
    document.querySelector(`button[onclick="openTab('${tabName}')"]`).classList.add('active');
}
