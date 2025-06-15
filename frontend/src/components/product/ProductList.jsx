import { Link } from 'react-router-dom';
import SearchForm from '../common/SearchForm';

import '../../styles/components/product/ProductList.scss';
import { useState } from 'react';

const ProductList = ({ products }) => {
  const [products, setProducts] = useState([]);

  return (
    <section>
      <SearchForm searchType={'product'} />
      <div className="item-list">
        {products.map(product => (
          <div className="item" key={product.productId}>
            <Link to={`/products/${product.productId}`}>
              <img
                src={
                  product.fileName
                    ? `${process.env.REACT_APP_API_BASE_URL}/upload/${product.fileName}`
                    : '/product_temp_128x128.png'
                }
                alt={product.productName}
              />
              <div className="card-body">
                <h5 className="card-title">{product.productName}</h5>
                <p className="card-text">
                  {new Intl.NumberFormat('ko-KR').format(product.price)} 포인트
                </p>
              </div>
            </Link>
          </div>
        ))}
      </div>
    </section>
  );
};

export default ProductList;
