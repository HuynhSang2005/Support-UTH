import { z } from 'zod';
import { TicketCategory, categorySchema } from './categories';

// Định nghĩa trạng thái của yêu cầu hỗ trợ
export enum TicketStatus {
  PENDING = 'pending',
  IN_PROGRESS = 'in_progress',
  RESOLVED = 'resolved',
  CLOSED = 'closed'
}
export const ticketStatusLabels: Record<TicketStatus, string> = {
  [TicketStatus.PENDING]: 'Đang chờ',
  [TicketStatus.IN_PROGRESS]: 'Đang xử lý',
  [TicketStatus.RESOLVED]: 'Đã giải quyết',
  [TicketStatus.CLOSED]: 'Đã đóng'
};

export interface TicketComment {
  id: string;
  ticketId: string;
  userId: string;
  content: string;
  createdAt: Date;
}

export interface Ticket {
  id: string;
  title: string;
  description: string;
  category: TicketCategory;
  status: TicketStatus;
  createdBy: string;
  assignedTo?: string;
  createdAt: Date;
  updatedAt: Date;
  comments: TicketComment[];
  categoryData?: Record<string, string>;
}

export const ticketSchema = z.object({
  title: z.string().min(1, 'Vui lòng nhập tiêu đề'),
  category: categorySchema,
  description: z.string().min(10, 'Mô tả phải có ít nhất 10 ký tự'),
  categoryData: z.record(z.string()).optional()
});