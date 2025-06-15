import { useState } from 'react';
import { setUrlBySearchType } from '../../utils/setUrlBySearchType';
import api from '../../api/axiosInstance';

import '../../styles/components/common/SearchForm.scss';

const SearchForm = ({ searchType }) => {
  const [keyword, setKeyword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [url, setUrl] = useState('');

  const handleSearchClick = async e => {
    e.preventDefault();

    if (!keyword) {
      setErrorMessage('검색어를 입력해주세요.');
      return;
    }

    setUrl(setUrlBySearchType(searchType, keyword));

    try {
      await api.get(url);
    } catch (error) {
      const message = error.response?.data?.message;
      alert(message);
    }
  };

  return (
    <div className="form-search-wrapper">
      <form className="form-search">
        <input id="input-search-content"
               type="text"
               placeholder="검색"
               onChange={e => setKeyword(e.target.value)} />
        <button type="submit"
                className="btn btn-primary"
                onClick={handleSearchClick}>
          <i className="bi bi-search"></i>
        </button>
      </form>
    </div>
  );
};

export default SearchForm;
