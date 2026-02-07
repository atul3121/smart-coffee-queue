import React from "react";

function QueueTable({ queue }) {
  return (
    <div className="card">
      <h3>Current Queue</h3>

      {queue.length === 0 ? (
        <p>No waiting orders</p>
      ) : (
        <table width="100%" border="1" cellPadding="8">
          <thead>
            <tr>
              <th>ID</th>
              <th>Drink</th>
              <th>Priority</th>
              <th>Waited (min)</th>
            </tr>
          </thead>
          <tbody>
            {queue.map((o) => (
              <tr key={o.id}>
                <td>{o.id}</td>
                <td>{o.drinkType}</td>
                <td>{o.priorityScore.toFixed(2)}</td>
                <td>{o.waitedMinutes}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default QueueTable;
