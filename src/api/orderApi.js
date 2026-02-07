const BASE_URL = "http://localhost:8081";

export async function placeOrder(drinkType) {
  const response = await fetch(
    `${BASE_URL}/orders?drinkType=${drinkType}`,
    { method: "POST" }
  );
  return response.json();
}

export async function fetchQueue() {
  const response = await fetch(`${BASE_URL}/orders/queue`);
  return response.json();
}

export async function fetchBaristas() {
  const response = await fetch(`${BASE_URL}/baristas`);
  return response.json();
}
