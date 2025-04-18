const Pagination = ({ currentPage, totalPages, onPageChange }) => {
  if (totalPages <= 1) return null;

  const handleClick = page => {
    if (page > totalPages) return;
    onPageChange(page);
  };

  return (
    <div className="pagination">
      {currentPage > 1 && (
        <button
          onClick={() => handleClick(currentPage - 1)}
          className="page-link"
        >
          &laquo;
        </button>
      )}

      {[...Array(totalPages)].map((_, i) => {
        const pageNumber = i + 1;
        return (
          <button
            key={pageNumber}
            onClick={() => handleClick(pageNumber)}
            className={`page-link ${currentPage === pageNumber ? 'active' : ''}`}
          >
            {pageNumber}
          </button>
        );
      })}

      {currentPage < totalPages && (
        <button
          onClick={() => handleClick(currentPage + 1)}
          className="page-link"
        >
          &raquo;
        </button>
      )}
    </div>
  );
};

export default Pagination;
