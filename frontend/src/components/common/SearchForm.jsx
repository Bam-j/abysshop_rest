import '../../styles/components/common/SearchForm.scss';

const SearchForm = ({searchType}) => {
  {/* searchType에 맞는 컨트롤러로 search 요청. product, adminXxxxxList, UserXxxxxList */}


  return (
    <div className="form-search-wrapper">
      <form className="form-search">
        <input id="input-search-content" type="text" placeholder="검색" />
        <button type="submit" className="btn btn-primary">
          <i className="bi bi-search"></i>
        </button>
      </form>
    </div>
  );
};

export default SearchForm;
