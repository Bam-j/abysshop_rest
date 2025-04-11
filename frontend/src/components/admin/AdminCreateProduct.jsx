import { useState, useRef } from 'react';
import axios from 'axios';

import '../../styles/components/admin/AdminCreateProduct.scss';

const AdminCreateProduct = () => {
  const [productName, setProductName] = useState('');
  const [productPrice, setProductPrice] = useState('');
  const [productDescription, setProductDescription] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const fileInputRef = useRef(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const files = fileInputRef.current?.files;
    if (!files || files.length === 0) {
      setErrorMessage('상품 이미지를 선택해주세요.');
      return;
    }

    if (!productName.trim()) {
      setErrorMessage('상품명을 입력해주세요.');
      return;
    }

    const priceValue = Number(productPrice);
    if (isNaN(priceValue)) {
      setErrorMessage('숫자만 입력 가능합니다.');
      return;
    } else if (priceValue <= 0) {
      setErrorMessage('0 이상의 가격을 입력해주세요.');
      return;
    }

    if (!productDescription.trim()) {
      setErrorMessage('상품 설명을 입력해주세요.');
      return;
    }

    const formData = new FormData();
    formData.append('image', files[0]);
    formData.append('productName', productName);
    formData.append('price', priceValue);
    formData.append('description', productDescription);

    try {
      const token = localStorage.getItem('accessToken');

      const response = await axios.post(
        'http://localhost:8080/api/admin/products/create',
        formData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
      );

      alert('상품이 성공적으로 등록되었습니다.');

      setProductName('');
      setProductPrice('');
      setProductDescription('');
      if (fileInputRef.current) {
        fileInputRef.current.value = null;
      }
    } catch (error) {
      const message = error.response?.data?.message || '상품 등록 중 오류가 발생했습니다.';
      setErrorMessage(message);
    }
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

        {errorMessage && <p className="error-message">{errorMessage}</p>}

        <button type="submit" className="btn btn-success">
          상품 등록
        </button>
      </form>
    </section>
  );
};

export default AdminCreateProduct;
