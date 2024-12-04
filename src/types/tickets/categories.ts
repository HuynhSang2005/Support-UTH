import { z } from 'zod';

export enum TicketCategory {
  STUDY_HOMEWORK = 'study_homework',
  CLASS_SCHEDULE = 'class_schedule',
  TUITION_PAYMENT = 'tuition_payment',
  STUDY_MATERIALS = 'study_materials',
  CAREER_COUNSELING = 'career_counseling',
  EXTRACURRICULAR = 'extracurricular',
  STUDENT_LIFE = 'student_life',
  TECHNICAL_SUPPORT = 'technical_support',
  LIBRARY_RESOURCES = 'library_resources',
  DORMITORY_HOUSING = 'dormitory_housing'
}

export const categoryLabels: Record<TicketCategory, string> = {
  [TicketCategory.STUDY_HOMEWORK]: 'Học tập và Bài tập',
  [TicketCategory.CLASS_SCHEDULE]: 'Lịch học và Thời khóa biểu',
  [TicketCategory.TUITION_PAYMENT]: 'Học phí và Thanh toán',
  [TicketCategory.STUDY_MATERIALS]: 'Tài liệu học tập',
  [TicketCategory.CAREER_COUNSELING]: 'Tư vấn nghề nghiệp',
  [TicketCategory.EXTRACURRICULAR]: 'Hoạt động ngoại khóa',
  [TicketCategory.STUDENT_LIFE]: 'Đời sống sinh viên',
  [TicketCategory.TECHNICAL_SUPPORT]: 'Kỹ thuật và CNTT',
  [TicketCategory.LIBRARY_RESOURCES]: 'Thư viện và Tài nguyên',
  [TicketCategory.DORMITORY_HOUSING]: 'Ký túc xá và Nhà ở'
};

export const categorySchema = z.nativeEnum(TicketCategory);

export interface CategoryField {
  name: string;
  type: 'text' | 'select' | 'date';
  label: string;
  required?: boolean;
  options?: { value: string; label: string }[];
}

export const categoryFields: Record<TicketCategory, CategoryField[]> = {
  [TicketCategory.STUDY_HOMEWORK]: [
    { name: 'subject', type: 'text', label: 'Môn học', required: true },
    { name: 'assignment', type: 'text', label: 'Tên bài tập', required: true }
  ],
  [TicketCategory.CLASS_SCHEDULE]: [
    { name: 'semester', type: 'text', label: 'Học kỳ', required: true }
  ],
  [TicketCategory.TUITION_PAYMENT]: [
    { name: 'semester', type: 'text', label: 'Học kỳ', required: true },
    { name: 'paymentType', type: 'select', label: 'Hình thức thanh toán', required: true,
      options: [
        { value: 'bank_transfer', label: 'Chuyển khoản ngân hàng' },
        { value: 'direct_payment', label: 'Thanh toán trực tiếp' }
      ]
    }
  ],
  [TicketCategory.STUDY_MATERIALS]: [
    { name: 'subject', type: 'text', label: 'Môn học', required: true }
  ],
  [TicketCategory.CAREER_COUNSELING]: [
    { name: 'counselingType', type: 'select', label: 'Loại tư vấn',
      options: [
        { value: 'career_path', label: 'Định hướng nghề nghiệp' },
        { value: 'internship', label: 'Thực tập' },
        { value: 'job_search', label: 'Tìm việc' }
      ]
    }
  ],
  [TicketCategory.EXTRACURRICULAR]: [
    { name: 'activityType', type: 'text', label: 'Loại hoạt động', required: true }
  ],
  [TicketCategory.STUDENT_LIFE]: [
    { name: 'concernType', type: 'text', label: 'Loại vấn đề', required: true }
  ],
  [TicketCategory.TECHNICAL_SUPPORT]: [
    { name: 'deviceType', type: 'text', label: 'Loại thiết bị', required: true },
    { name: 'issueType', type: 'text', label: 'Loại sự cố', required: true }
  ],
  [TicketCategory.LIBRARY_RESOURCES]: [
    { name: 'resourceType', type: 'text', label: 'Loại tài nguyên', required: true }
  ],
  [TicketCategory.DORMITORY_HOUSING]: [
    { name: 'buildingNumber', type: 'text', label: 'Số tòa nhà', required: true },
    { name: 'roomNumber', type: 'text', label: 'Số phòng', required: true }
  ]
};