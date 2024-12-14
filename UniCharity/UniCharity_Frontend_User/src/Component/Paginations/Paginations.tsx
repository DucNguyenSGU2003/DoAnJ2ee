import React from 'react';
import './Paginations.scss';

export type Props = {
    totalItem: number;
    currentPage: number;
    totalPages: number;
    pageSize: number;
    onPageChange: (pageNumber: number) => void;
};

const Paginations: React.FC<Props> = ({ totalItem, currentPage, totalPages, pageSize, onPageChange }) => {
    const handlePageClick = (pageNumber: number) => {
        if (pageNumber >= 0 && pageNumber < totalPages) {
            onPageChange(pageNumber);
        }
    };

    return (
        <div className='my-page mt-4'>
            <div className="container-page">
                <ul className="pagination">
                    {/* Nút Prev */}
                    <li className={currentPage === 0 ? 'disabled' : ''}>
                        <a onClick={() => handlePageClick(currentPage - 1)}>Prev</a>
                    </li>

                    {/* Các nút số trang */}
                    {Array.from({ length: totalPages }).map((_, index) => (
                        <li key={index} className={index === currentPage ? 'active' : ''}>
                            <a onClick={() => handlePageClick(index)}>{index + 1}</a>
                        </li>
                    ))}

                    {/* Nút Next */}
                    <li className={currentPage === totalPages - 1 ? 'disabled' : ''}>
                        <a onClick={() => handlePageClick(currentPage + 1)}>Next</a>
                    </li>
                </ul>
            </div>
        </div>
    );
};

export default Paginations;
