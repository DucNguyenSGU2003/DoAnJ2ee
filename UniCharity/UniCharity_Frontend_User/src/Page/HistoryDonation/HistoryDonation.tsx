import React, { useEffect, useState } from 'react';
import { useAuth } from '../../Context/UseAuth';
import { toast } from 'react-toastify';
import { HistoryDonationGetAPI } from '../../Service/HistoryService';
import './HistoryDonation.scss';
import { HistoryDonation } from '../../Models/History';

const HistoryDonationComponent = () => {
    const { user, isLoggedIn } = useAuth();
    const [historyDonation, setHistoryDonation] = useState<HistoryDonation[]>([]); // State lưu danh sách lịch sử
    const [visibleCount, setVisibleCount] = useState(10); // State quản lý số lượng phần tử hiển thị

    useEffect(() => {
        if (!isLoggedIn()) {
            toast.warning('Vui lòng đăng nhập!');
            return;
        }

        // Gọi API lấy lịch sử quyên góp
        HistoryDonationGetAPI(user?.id!)
            .then((res) => {
                if (res?.data) {
                    console.log(res?.data.result.items);
                    setHistoryDonation(res?.data.result.items);
                }
            })
            .catch(() => {
                toast.error('Lỗi khi tải lịch sử quyên góp!');
            });
    }, [isLoggedIn, user]);

    // Hàm xử lý khi nhấn "Xem thêm"
    const handleLoadMore = () => {
        setVisibleCount((prev) => prev + 5); // Tăng số lượng phần tử hiển thị thêm 5
    };

    return (
        <div className="justify-content-center d-flex">
            <div className="my-table p-4">
                <div id="wrapper">
                    <div className="custom-text-box d-flex justify-content-center py-3">
                        <h5 className="fw-bold" style={{ color: "var(--primary-color)", fontSize: "42px" }}>
                            Lịch Sử Quyên Góp Của Bạn
                        </h5>
                    </div>
                    <table id="keywords" cellSpacing={0}>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>Tên Quyên Góp</th>
                                <th>Số Tiền</th>
                                <th>Ngày Quyên Góp</th>
                            </tr>
                        </thead>
                        <tbody>
                            {historyDonation.length > 0 ? (
                                historyDonation
                                    .sort((a, b) => new Date(b.donationDate).getTime() - new Date(a.donationDate).getTime())
                                    .slice(0, visibleCount) // Chỉ hiển thị tối đa `visibleCount` phần tử
                                    .map((hisDona, index) => (
                                        <tr key={hisDona.id}>
                                            <td>{index + 1}</td>
                                            <td>{hisDona.campaign.title}</td>
                                            <td>
                                                {hisDona.amount.toLocaleString('vi-VN', {
                                                    style: 'currency',
                                                    currency: 'VND',
                                                })}
                                            </td>
                                            <td>{new Date(hisDona.donationDate).toLocaleDateString('vi-VN')}</td>
                                        </tr>
                                    ))
                            ) : (
                                <tr>
                                    <td colSpan={4} className="text-center">
                                        Không có lịch sử quyên góp nào
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                    {/* Nút "Xem thêm" */}
                    {visibleCount < historyDonation.length && (
                        <div className="d-flex justify-content-center pb-4" >
                            <button onClick={handleLoadMore} className="btn custom-btn mt-3">
                                Xem thêm
                            </button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};

export default HistoryDonationComponent;
