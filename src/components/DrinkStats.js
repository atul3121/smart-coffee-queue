import React, { useEffect, useState } from "react";

function DrinkStats() {
  const [stats, setStats] = useState({});

  useEffect(() => {
    fetch("http://localhost:8081/stats")
      .then(res => res.json())
      .then(data => setStats(data.ordersByDrinkType || {}));
  }, []);

  return (
    <div className="card">
      <h2>☕ Orders by Drink Type</h2>
      <ul>
        {Object.entries(stats).map(([drink, count]) => (
          <li key={drink}>
            {drink}: <b>{count}</b>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default DrinkStats;
