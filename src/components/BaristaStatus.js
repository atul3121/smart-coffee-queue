import React from "react";

function BaristaStatus({ baristas }) {
  return (
    <div className="card">
      <h3>Barista Status</h3>

      {baristas.map((b) => (
        <p key={b.id}>
          {b.name} — {b.busy ? "☕ Busy" : "✅ Free"}
        </p>
      ))}
    </div>
  );
}

export default BaristaStatus;
