import React from 'react'
import { useForm } from 'react-hook-form'
import * as yup from 'yup'
import { yupResolver } from '@hookform/resolvers/yup'
import { useAuth } from '../../Context/UseAuth'
import { CampaignFormRequest } from '../../Models/Campaign'
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom'
import { CampaignRequest } from '../../Service/CampaignService'

const validationSchema = yup.object().shape({
    title: yup
        .string()
        .required('Tiêu đề là bắt buộc.')
        .max(100, 'Tiêu đề không được vượt quá 100 ký tự.'),
    description: yup.string().required('Mô tả là bắt buộc.'),
    targetAmount: yup
        .number()
        .required('Số tiền mục tiêu là bắt buộc.')
        .positive('Số tiền mục tiêu phải lớn hơn 0.'),
    createdBy: yup
        .number()
        .required('Người tạo là bắt buộc.')
        .positive('ID của người tạo phải lớn hơn 0.')
})


const CreateCampaign = () => {
    const { register, handleSubmit, formState: { errors } } = useForm<CampaignFormRequest>({
        resolver: yupResolver(validationSchema)
    })
    const navigate = useNavigate()

    const { isLoggedIn, user } = useAuth()

    const onSubmit = (data: CampaignFormRequest) => {
        CampaignRequest(data)
            .then((response) => {
                if (response?.data.code === 1000) {
                    toast.success('Yêu cầu thành công!');
                    navigate('/'); 
                } else {
                    toast.error('Đã xảy ra lỗi khi tạo chiến dịch.');
                }
            })
            .catch((error) => {
                toast.error('Lỗi kết nối tới server.');
            });
    }

    return (
        isLoggedIn() 
        ?
        <section className="volunteer-section section-padding" id="section_4">
            <div className="container">
                <div className="row">
                    <div className="col-lg-6 col-12">
                        <h2 className="text-white mb-4">Yêu cầu tạo 1 chiến dịch Campaign</h2>

                        <form
                            className="custom-form volunteer-form mb-5 mb-lg-0"
                            onSubmit={handleSubmit(onSubmit)} // Gọi hàm handleSubmit với onSubmit
                        >
                            <div className="mb-3">
                                <input
                                    type="text"
                                    id="title"
                                    className="form-control"
                                    placeholder="Tiêu đề chiến dịch"
                                    {...register('title')}
                                />
                                {errors.title && (
                                    <p className="text-danger">{errors.title.message}</p>
                                )}
                            </div>

                            <div className="mb-3">
                                <textarea
                                    id="description"
                                    className="form-control"
                                    placeholder="Mô tả chiến dịch (bao gồm thông tin đối tượng, họ và tên, mã sinh viên, ...)"
                                    rows={4}
                                    {...register('description')}
                                />
                                {errors.description && (
                                    <p className="text-danger">{errors.description.message}</p>
                                )}
                            </div>

                            <div className="mb-3">
                                <input
                                    type="number"
                                    id="targetAmount"
                                    className="form-control"
                                    placeholder="Số tiền mục tiêu (VNĐ)"
                                    {...register('targetAmount')}
                                />
                                {errors.targetAmount && (
                                    <p className="text-danger">{errors.targetAmount.message}</p>
                                )}
                            </div>

                            <div className="mb-3 d-none">
                                <input
                                    type="number"
                                    id="createdBy"
                                    className="form-control"
                                    placeholder="ID người tạo"
                                    value={user?.id}
                                    {...register('createdBy')}
                                />
                                {errors.createdBy && (
                                    <p className="text-danger">{errors.createdBy.message}</p>
                                )}
                            </div>

                            <button type="submit" className="form-control btn btn-primary">
                                Tạo Campaign
                            </button>
                        </form>
                    </div>

                    <div className="col-lg-6 col-12">
                        <img
                            src="images/smiling-casual-woman-dressed-volunteer-t-shirt-with-badge.jpg"
                            className="volunteer-image img-fluid"
                            alt=""
                        />

                        <div className="custom-block-body text-center">
                            <h4 className="text-white mt-lg-3 mb-lg-3">Về chiến dịch</h4>
                            <p className="text-white">
                                Bắt đầu một chiến dịch từ thiện để giúp đỡ những người cần sự hỗ trợ.
                                Mỗi chiến dịch đều mang ý nghĩa và giá trị lớn lao!
                            </p>
                        </div>
                    </div>

                </div>
            </div>
        </section>
        : <div className='d-flex justify-content-center align-items-center' >
            <h2>Please Login</h2>
        </div>
    )
}

export default CreateCampaign
