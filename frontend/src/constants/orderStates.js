export const ORDER_STATE = {
  PENDING: 'PENDING',
  COMPLETED: 'COMPLETED',
  REFUNDED: 'REFUNDED',
};

export const ORDER_STATE_LABEL = {
  [ORDER_STATE.PENDING]: '상품 지급 대기',
  [ORDER_STATE.COMPLETED]: '상품 지급 완료',
  [ORDER_STATE.REFUNDED]: '환불 처리 완료',
};
