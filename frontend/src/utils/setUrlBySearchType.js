export const setUrlBySearchType = (searchType, keyword) => {
  let url;

  if (searchType === 'product') {
    url = '/products/search';
  }
  /*
    TODO:
      userOrderList, userPointRechargeList,
      adminOrderList, adminPointRechargeList, adminPointRechargeDetailList
      에 대한 검색 기준 만들고(ID or Name) 조건 분기 완성시키기
   */

  url += '?keyword=' + keyword;
  return url;
};