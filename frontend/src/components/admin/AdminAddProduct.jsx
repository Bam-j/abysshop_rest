import { useState, useRef } from 'react';

import '../../styles/components/admin/AdminAddProduct.scss';

const AdminAddProduct = () => {
  const [productName, setProductName] = useState('');
  const [productPrice, setProductPrice] = useState('');
  const [productDescription, setProductDescription] = useState('');
  const fileInputRef = useRef(null);

  const handleSubmit = async e => {
    e.preventDefault();

    if (!productName.trim()) {
      alert('상품명을 입력해주세요.');
      return;
    }
    if (!productPrice.trim() || isNaN(productPrice.value) || Number(
      productPrice.value) <= 0) {
      alert('유효한 가격(숫자)를 입력해주세요.');
      return;
    }
    if (!productDescription.trim()) {
      alert('상품 설명을 입력해주세요.');
      return;
    }

    //TODO: 추후 요청, 데이터는 백엔드 API 설계 이후 재작성
  };

  return (
    <section className="add-product">
      <h2>상품 추가</h2>
      <hr />
      <form onSubmit={handleSubmit}>
        <label htmlFor="product-image" className="form-label mt-4">
          상품 이미지:
        </label>
        <input type="file" id="product-image" className="form-control"
               ref={fileInputRef} multiple />
        <hr />
        <input
          type="text"
          id="product-name"
          className="form-control"
          placeholder="상품명"
          value={productName}
          onChange={e => setProductName(e.target.value)}
        />
        <input
          type="text"
          id="product-price"
          className="form-control"
          placeholder="상품 가격"
          value={productPrice}
          onChange={e => setProductPrice(e.target.value)}
        />
        <textarea
          id="product-description"
          className="form-control"
          placeholder="상품 설명"
          value={productDescription}
          onChange={e => setProductDescription(e.target.value)}
        />
        <button type="submit" className="btn btn-success">
          상품 등록
        </button>
      </form>
    </section>
  );
};

export default AdminAddProduct;
