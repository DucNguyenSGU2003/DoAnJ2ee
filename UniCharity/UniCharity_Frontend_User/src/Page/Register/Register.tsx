import React from 'react';
import { useForm } from 'react-hook-form';
import * as yup from 'yup';
import { yupResolver } from '@hookform/resolvers/yup';
import { useAuth } from '../../Context/UseAuth';

export type UserRegisterRequest = {
  name: string;
  email: string;
  phone: string;
  password: string;
  role: string;
};

// Xác thực form với Yup
const validationSchema = yup.object().shape({
  name: yup.string().required('Tên là bắt buộc.'),
  email: yup
    .string()
    .email('Vui lòng nhập một email hợp lệ.')
    .required('Email là bắt buộc.'),
  phone: yup
    .string()
    .required('Số điện thoại là bắt buộc.')
    .matches(/^\d{10}$/, 'Số điện thoại phải có 10 chữ số.'),
  password: yup
    .string()
    .required('Mật khẩu là bắt buộc.')
    .min(6, 'Mật khẩu phải có ít nhất 6 ký tự.')
});

// Đặt giá trị mặc định cho role là "user"
const Register = () => {
  const { registerUser } = useAuth();

  const {
    register,
    handleSubmit,
    formState: { errors }
  } = useForm<UserRegisterRequest>({
    resolver: yupResolver(validationSchema),
    defaultValues: {
      role: 'user'
    }
  });

  const onSubmit = (data: UserRegisterRequest) => {
    registerUser(data)
  };

  return (
    <section className="volunteer-section section-padding" id="section_4">
      <div className="container">
        <div className="row">
          <div className="col-lg-6 col-12">
            <h2 className="text-white mb-4">Đăng Ký</h2>
            <p className="text-white mb-4">
              Hãy đăng ký để tham gia cộng đồng và đóng góp cho xã hội.
            </p>

            <form
              className="custom-form volunteer-form mb-5 mb-lg-0"
              onSubmit={handleSubmit(onSubmit)}
            >
              <div className="mb-3">
                <input
                  type="text"
                  id="name"
                  className="form-control"
                  placeholder="Nhập tên của bạn"
                  {...register('name')}
                />
                {errors.name && <p className="text-danger">{errors.name.message}</p>}
              </div>

              <div className="mb-3">
                <input
                  type="email"
                  id="email"
                  className="form-control"
                  placeholder="Nhập email của bạn"
                  {...register('email')}
                />
                {errors.email && <p className="text-danger">{errors.email.message}</p>}
              </div>

              <div className="mb-3">
                <input
                  type="text"
                  id="phone"
                  className="form-control"
                  placeholder="Nhập số điện thoại của bạn"
                  {...register('phone')}
                />
                {errors.phone && <p className="text-danger">{errors.phone.message}</p>}
              </div>

              <div className="mb-3">
                <input
                  type="password"
                  id="password"
                  className="form-control"
                  placeholder="Nhập mật khẩu của bạn"
                  {...register('password')}
                />
                {errors.password && <p className="text-danger">{errors.password.message}</p>}
              </div>

              {/* Vai trò mặc định luôn là "user", không cho phép thay đổi */}
              <input type="hidden" value="user" {...register('role')} />

              <button type="submit" className="form-control btn btn-primary">
                Đăng Ký
              </button>
            </form>
          </div>

          <div className="col-lg-6 col-12">
            <img
              src="images/smiling-casual-woman-dressed-volunteer-t-shirt-with-badge.jpg"
              className="volunteer-image img-fluid"
              alt="Hình ảnh từ thiện"
            />

            <div className="custom-block-body text-center">
              <h4 className="text-white mt-lg-3 mb-lg-3">Tại sao nên tham gia?</h4>
              <p className="text-white">
                Cùng nhau xây dựng một thế giới tốt đẹp hơn qua những hoạt động từ thiện.
                Hãy tham gia để lan tỏa sự yêu thương và mang lại hy vọng cho mọi người.
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Register;
