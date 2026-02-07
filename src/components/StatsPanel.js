import React, { useEffect, useState } from "react";

function StatsPanel() {
  const [stats, setStats] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8081/stats")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to fetch stats");
        }
        return res.json();
      })
      .then((data) => setStats(data))
      .catch((err) => setError(err.message));
  }, []);

  if (error) {
    return <p style={{ color: "red" }}>❌ {error}</p>;
  }

  if (!stats) {
    return <p>Loading performance metrics...</p>;
  }

  return (
    <div className="card">
      <h2>📊 Performance Metrics</h2>

      <p>
        Average Wait Time:{" "}
        <b>{stats.averageWaitTime.toFixed(2)} minutes</b>
      </p>

      <p>
        SLA Violations ( &gt; 10 min ):{" "}
        <b>{stats.slaViolations}</b>
      </p>

      <h3>👨‍🍳 Orders per Barista</h3>
      <ul>
        {Object.entries(stats.ordersPerBarista).map(([baristaId, count]) => (
          <li key={baristaId}>
            Barista {baristaId}: {count} orders
          </li>
        ))}
      </ul>
    </div>
  );
}

export default StatsPanel;
