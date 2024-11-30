// Định nghĩa trạng thái của yêu cầu hỗ trợ
export enum TicketStatus {
  PENDING = 'pending',
  IN_PROGRESS = 'in_progress',
  RESOLVED = 'resolved',
  CLOSED = 'closed'
}

// Định nghĩa danh mục yêu cầu hỗ trợ
export enum TicketCategory {
  ACADEMIC = 'academic',
  ADMINISTRATIVE = 'administrative',
  TECHNICAL = 'technical',
  FINANCIAL = 'financial'
}

// Định nghĩa kiểu dữ liệu cho bình luận trong yêu cầu
export interface TicketComment {
  id: string;
  ticketId: string;
  userId: string;
  content: string;
  createdAt: Date;
}

// Định nghĩa kiểu dữ liệu cho yêu cầu hỗ trợ
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
}