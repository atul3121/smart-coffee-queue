import React, { useEffect, useState } from "react";
import OrderForm from "../components/OrderForm";
import QueueTable from "../components/QueueTable";
import BaristaStatus from "../components/BaristaStatus";
import StatsPanel from "../components/StatsPanel";
import OrderHistory from "../components/OrderHistory";
import DrinkStats from "../components/DrinkStats";
import { fetchQueue, fetchBaristas } from "../api/orderApi";

function Dashboard() {
  const [queue, setQueue] = useState([]);
  const [baristas, setBaristas] = useState([]);

  async function loadData() {
    setQueue(await fetchQueue());
    setBaristas(await fetchBaristas());
  }

  useEffect(() => {
    loadData();
    const interval = setInterval(loadData, 3000);
    return () => clearInterval(interval);
  }, []);

  return (
    <div className="container">
      <h1>☕ Smart Coffee Queue System</h1>

      <OrderForm onOrderPlaced={loadData} />
      <QueueTable queue={queue} />
      <BaristaStatus baristas={baristas} />
      
      <OrderHistory />
      <DrinkStats />
      <StatsPanel/>
    </div>
  );
}

export default Dashboard;
