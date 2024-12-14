import React from 'react'
import { useNavigate } from 'react-router-dom'

const Sidebar = () => {
    const navigate = useNavigate()
    return (

        <div className="shadow sidebar pointer pe-4 pb-3">
            <nav className="navbar bg-light navbar-light">
                <a href="index.html" className="navbar-brand mx-4 mb-3">
                    <h3 className="text-primary"><i className="fa fa-hashtag me-2"></i>DASHMIN</h3>
                </a>
                <div className="d-flex align-items-center ms-4 mb-4">
                    <div className="position-relative">
                        <img className="rounded-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSADESsnqLTB7q95kJhJXXqRra6IqT3zbBhRA&s" alt="" style={{ width: "40px", height: "40px" }} />
                        <div className="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                    <div className="ms-3">
                        <h6 className="mb-0">Jhon Doe</h6>
                        <span>Admin</span>
                    </div>
                </div>
                <div className="navbar-nav w-100">
                    <div className="nav-item dropdown">
                        {/* <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-laptop me-2"></i>Campaign</a> */}
                        {/* <div className="dropdown-menu bg-transparent border-0">
                            <a onClick={() => navigate("/admin/campaigns")} className="dropdown-item">Campaign</a>
                            <a onClick={() => navigate("/admin/Reports")} className="dropdown-item">Report</a>
                            <a onClick={() => navigate("/admin/policies")} className="dropdown-item">Policies</a>
                            <a onClick={() => navigate("/admin/violations")} className="dropdown-item">Violations</a>
                            <a onClick={() => navigate("/admin/donation")} className="dropdown-item">Donation</a>

                        </div> */}
                    </div>
                    <a onClick={() => navigate("/admin/campaigns")} className="nav-item nav-link"><i className="fa fa-th me-2"></i>Campaign</a>
                    <a onClick={() => navigate("/admin/policies")} className="nav-item nav-link"><i className="fa fa-th me-2"></i>Policy</a>
                    <a onClick={() => navigate("/admin/donations")} className="nav-item nav-link"><i className="fa fa-table me-2"></i>Donation</a>
                    <a onClick={() => navigate("/admin/reports")} className="nav-item nav-link"><i className="fa fa-table me-2"></i>Report</a>
                    {/* <a onClick={() => navigate("/admin/violation")} className="nav-item nav-link"><i className="fa fa-table me-2"></i>Violation</a> */}
                    <div className="nav-item dropdown">
                        <a href="#" className="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i className="fa fa-chart-bar me-2"></i>Chart</a>
                        <div className="dropdown-menu bg-transparent border-0">
                            <a onClick={() => navigate("/admin/chart/top")} className="dropdown-item">Top</a>
                            <a onClick={() => navigate("/admin/chart/year")} className="dropdown-item">Year</a>

                        </div>
                    </div>
                </div>
            </nav>
        </div>
    )
}

export default Sidebar
