import React, { useState } from 'react'
import './Login.scss'
import { useAuth } from '../../Context/UseAuth';
import { useNavigate } from 'react-router';

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const { login } = useAuth()
    const navigate = useNavigate()

    const handleSubmit = (e: any) => {
        // login(email, password)
        navigate("/admin/chart/year")
    }

    return (
        <div className='my-login'>
            <body className="align">
                <div className="grid align__item">
                    <div className="register">
                        <svg xmlns="http://www.w3.org/2000/svg" className="site__logo" width="56" height="84" viewBox="77.7 214.9 274.7 412">
                            <defs>
                                <linearGradient id="a" x1="0%" y1="0%" y2="0%">
                                    <stop offset="0%" stop-color="#8ceabb" />
                                    <stop offset="100%" stop-color="#378f7b" />
                                </linearGradient>
                            </defs>
                            <path fill="url(#a)" d="M215 214.9c-83.6 123.5-137.3 200.8-137.3 275.9 0 75.2 61.4 136.1 137.3 136.1s137.3-60.9 137.3-136.1c0-75.1-53.7-152.4-137.3-275.9z" />
                        </svg>
                        <h2>Login</h2>
                        <form onSubmit={handleSubmit} className="form">
                            <div className="form__field">
                                <input
                                    type="email"
                                    placeholder="info@mailaddress.com"
                                    value={email} // Gắn giá trị state vào input
                                    onChange={(e) => setEmail(e.target.value)} // Cập nhật giá trị email khi người dùng nhập
                                />
                            </div>

                            <div className="form__field">
                                <input
                                    type="password"
                                    placeholder="••••••••••••"
                                    value={password} // Gắn giá trị state vào input
                                    onChange={(e) => setPassword(e.target.value)} // Cập nhật giá trị password khi người dùng nhập
                                />
                            </div>

                            <div className="form__field">
                                <input type="submit" value="Login" />
                            </div>
                        </form>
                    </div>
                </div>
            </body>
        </div>
    )
}

export default LoginPage
