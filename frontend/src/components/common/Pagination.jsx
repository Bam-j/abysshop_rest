import '../../styles/components/common/Pagination.scss';

const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  return (
    <div className="pagination">
      {currentPage > 0 && (
        <button className="page-link" onClick={() => onPageChange(currentPage - 1)}>
          &laquo;
        </button>
      )}
      {Array.from({ length: totalPages }, (_, i) => (
        <button
          key={i}
          className={`page-link ${currentPage === i ? 'active' : ''}`}
          onClick={() => onPageChange(i)}
        >
          {i + 1}
        </button>
      ))}
      {currentPage < totalPages - 1 && (
        <button className="page-link" onClick={() => onPageChange(currentPage + 1)}>
          &raquo;
        </button>
      )}
    </div>
  );
};

export default Pagination;
