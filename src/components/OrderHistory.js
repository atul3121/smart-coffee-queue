import React, { useEffect, useState } from "react";

function OrderHistory() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8081/orders/history")
      .then(res => res.json())
      .then(setOrders);
  }, []);

  return (
    <div className="card">
      <h2>📜 Completed Orders</h2>

      <table>
        <thead>
          <tr>
            <th>Drink</th>
            <th>Barista</th>
            <th>Wait (min)</th>
            <th>Prep (min)</th>
            <th>Total (min)</th>
          </tr>
        </thead>

        <tbody>
          {orders.map(o => {
            const wait =
              (new Date(o.startTime) - new Date(o.arrivalTime)) / 60000;

            const prep =
              (new Date(o.endTime) - new Date(o.startTime)) / 60000;

            const total =
              (new Date(o.endTime) - new Date(o.arrivalTime)) / 60000;

            return (
              <tr key={o.id}>
                <td>{o.drinkType}</td>
                <td>{o.baristaId}</td>
                <td>{wait.toFixed(1)}</td>
                <td>{prep.toFixed(1)}</td>
                <td>{total.toFixed(1)}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
}

export default OrderHistory;
