import { Ticket, TicketStatus } from '../types/tickets/ticket';
import { TicketCategory } from '../types/tickets/categories';

export const mockTickets: Ticket[] = [
  {
    id: '1',
    title: 'Hỗ trợ đăng ký học phần',
    description: 'Em cần hỗ trợ về việc đăng ký học phần học kỳ 2',
    category: TicketCategory.STUDY_HOMEWORK,
    status: TicketStatus.PENDING,
    createdBy: '1',
    createdAt: new Date('2024-12-01'),
    updatedAt: new Date('2024-12-01'),
    comments: []
  },
  {
    id: '2',
    title: 'Vấn đề về học phí',
    description: 'Em cần xác nhận về khoản học phí học kỳ này',
    category: TicketCategory.TUITION_PAYMENT,
    status: TicketStatus.IN_PROGRESS,
    createdBy: '1',
    assignedTo: '2',
    createdAt: new Date('2024-12-02'),
    updatedAt: new Date('2024-12-03'),
    comments: [
      {
        id: '1',
        ticketId: '2',
        userId: '2',
        content: 'Đã tiếp nhận yêu cầu, sẽ kiểm tra và phản hồi sớm',
        createdAt: new Date('2024-12-03')
      }
    ]
  }
];