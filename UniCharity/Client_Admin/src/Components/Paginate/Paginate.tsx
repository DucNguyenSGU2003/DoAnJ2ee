/* eslint-disable prefer-const */
/* eslint-disable @typescript-eslint/no-explicit-any */
import "./Paginate.scss";
import { PageObject } from "../../Models/Paginate";

export type PagingProps = {
  page: PageObject;
  onPageChange: (page: number) => void;
};

const Paginate = ({ page, onPageChange }: PagingProps) => {
  const handlePrevious = () => {
    if (page.currentPage >= 1) {
      onPageChange(page.currentPage - 1);
    }
  };

  const handleNext = () => {
    if (page.currentPage < page.totalPages-1) {
      onPageChange(page.currentPage + 1);
    }
  };

  const renderPageNumbers = () => {
    let pages: any[] = [];
    for (let i = 0; i < page.totalPages; i++) {
      pages.push(
        <button
          key={i}
          className={`pagination__number ${
            i === page.currentPage ? "pagination__number--active" : ""
          }`}
          onClick={() => onPageChange(i)}
        >
          {i}
        </button>
      );
    }
    return pages;
  };

  return (
    <div className="my-page">
      <div className="pagination" style={{ boxShadow: "none" }}>
        <span className="pagination__number-indicator">
          Page {page.currentPage} of {page.totalPages} (Total Items:{" "}
          {page.totalItems})
        </span>

        <button
          className="pagination__arrow"
          disabled={page.currentPage === 0}
          onClick={handlePrevious}
        >
          <span className="pagination__arrow-half"></span>
          <span className="pagination__arrow-half"></span>
        </button>

        {renderPageNumbers()}

        <button
          className="pagination__arrow pagination__arrow--right"
          disabled={page.currentPage === page.totalPages}
          onClick={handleNext}
        >
          <span className="pagination__arrow-half"></span>
          <span className="pagination__arrow-half"></span>
        </button>
      </div>
    </div>
  );
};

export default Paginate;
