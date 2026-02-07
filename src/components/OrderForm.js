import React, { useState } from "react";

function OrderForm({ onOrderPlaced }) {
  const [drinkType, setDrinkType] = useState("ESPRESSO");

  const placeOrder = async () => {
    await fetch(
      `http://localhost:8081/orders?drinkType=${drinkType}`,
      {
        method: "POST"
      }
    );

    onOrderPlaced(); // refresh UI
  };

  return (
    <div className="card">
      <h2>🧾 Place Order</h2>

      <select
        value={drinkType}
        onChange={(e) => setDrinkType(e.target.value)}
      >
        <option value="COLD_BREW">Cold Brew</option>
        <option value="ESPRESSO">Espresso</option>
        <option value="AMERICANO">Americano</option>
        <option value="CAPPUCCINO">Cappuccino</option>
        <option value="LATTE">Latte</option>
        <option value="SPECIALTY">Specialty</option>
      </select>

      <button onClick={placeOrder}>Place Order</button>
    </div>
  );
}

export default OrderForm;
