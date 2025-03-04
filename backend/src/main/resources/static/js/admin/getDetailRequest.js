const getDetailRequest = rechargeId => {
  const url = `/point/recharge/detail?rechargeId=${rechargeId}`
  console.log("Fetching:", url);

  fetch(url, {method: "GET", headers: { "Accept": "application/json" }})
  .then(response => {
    console.log("Response received:", response);
    console.log("Response status:", response.status);
    console.log("Content-Type:", response.headers.get("content-type"));

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }
    return response.json();
  })
  .then(data => {
    console.log("Received data:", data);

    document.querySelector(
        "#manage-recharge-detail-modal input[name='rechargeId']").value = data.rechargeId;
    document.querySelector(
        "#manage-recharge-detail-modal input[name='userId']").value = data.userId;
    document.querySelector(
        "#manage-recharge-detail-modal input[name='bank']").value = data.bank
        || '';
    document.querySelector(
        "#manage-recharge-detail-modal input[name='accountNumber']").value = data.accountNumber
        || '';

    console.log("Updated modal fields.");
  })
  .catch(error => {
    console.error(error);
  });
}
