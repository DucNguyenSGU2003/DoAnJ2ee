import React from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../../Context/UseAuth'
import DefaultUser from '/images/avatar/DefaultUser.png'

const Navbar = () => {
    const navigate = useNavigate()
    const { isLoggedIn, logout, user } = useAuth()

    const handleLoggout = () => {
        logout()
    }

    return (
        <div className="navbar navbar-expand-lg bg-light shadow-lg">
            <div className="container">
                <a className="navbar-brand" href="index.html">
                    <img src="/images/logo.png" className="logo img-fluid" alt="Kind Heart Charity" />
                    <span>
                        Đại Học Sài Gòn Charity
                        <small>Non-profit Organization</small>
                    </span>
                </a>

                <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>

                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav ms-auto">
                        <li className="nav-item">
                            <a className="nav-link click-scroll"
                                style={{ cursor: "pointer" }}
                                onClick={() => navigate("/")}
                            >Home</a>
                        </li>

                        <li className="nav-item">
                            <a className="nav-link click-scroll"
                                style={{ cursor: "pointer" }}
                                onClick={() => navigate("/about")}
                            >About</a>
                        </li>

                        <li className="nav-item">
                            <a className="nav-link click-scroll"
                                style={{ cursor: "pointer" }}
                                onClick={() => navigate("/request-campaign")}
                            >Request Campaign</a>
                        </li>

                        <li className="nav-item">
                            <a className="nav-link click-scroll"
                                onClick={() => navigate("/history-donation")}
                            >History donation</a>
                        </li>

                        {isLoggedIn() ? (
                            <div className="btn-group">
                                <div className="dropdown">
                                    <button style={{ color: "#5bc1ac", marginTop: "13px" }} className="btn dropdown-toggle btn-group"
                                        type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        {user?.name || "Dropdown"}
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        {/* <a className="dropdown-item" href="#">Profile</a> */}
                                        <a className="dropdown-item"
                                            style={{ cursor: "pointer" }}
                                            onClick={handleLoggout}
                                        >Log out</a>
                                    </div>
                                </div>

                                <div className="btn-group mx-2 btn-group mx-2 d-flex align-items-center">
                                    <img src={DefaultUser} style={{ height: "36px", width: "36px" }} alt="" />
                                </div>
                            </div>


                        ) : <>
                            <li className="nav-item ms-3"
                                onClick={() => navigate("/login")}
                            >
                                <a className="nav-link custom-btn custom-border-btn btn">Login</a>
                            </li>

                            <li className="nav-item ms-3"
                                onClick={() => navigate("/register")}
                            >
                                <a className="nav-link custom-btn custom-border-btn btn">Register</a>
                            </li>
                        </>}
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default Navbar
