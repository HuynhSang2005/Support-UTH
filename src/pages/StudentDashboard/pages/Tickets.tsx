import {
  Card,
  CardContent,
  CardHeader,
  CardTitle,
} from "../../../components/ui/card";
import { TicketForm } from "../components/TicketSubmission/TicketForm";
import { useTicketContext } from "../../../contexts/TicketContext";
import { useAuth } from "../../../hooks/useAuth";
import { Ticket, TicketStatus } from "../../../types/tickets/ticket";

export default function StudentTickets() {
  const { user } = useAuth();
  const { addTicket } = useTicketContext();

  if (!user) return null;

  return (
    <div className="space-y-6">
      <h1 className="text-3xl font-bold">Gửi yêu cầu hỗ trợ</h1>

      <Card>
        <CardHeader>
          <CardTitle>Thông tin sinh viên</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="grid gap-4 text-sm">
            <div>
              <span className="font-medium">Họ và tên:</span> {user.fullName}
            </div>
            <div>
              <span className="font-medium">Email:</span> {user.email}
            </div>
            <div>
              <span className="font-medium">Mã số sinh viên:</span>{" "}
              {user.studentId}
            </div>
            <div>
              <span className="font-medium">Lớp:</span> {user.class}
            </div>
          </div>
        </CardContent>
      </Card>

      <Card>
        <CardHeader>
          <CardTitle>Form yêu cầu</CardTitle>
        </CardHeader>
        <CardContent>
          <TicketForm
            onSubmit={(data) => {
              // Tạo ticket mới với thông tin cần thiết
              const newTicket: Ticket = {
                id: Date.now().toString(), // Tạm thời dùng timestamp làm id
                ...data,
                status: TicketStatus.PENDING,
                createdBy: user.id,
                createdAt: new Date(),
                updatedAt: new Date(),
                comments: [],
              };

              // Thêm ticket vào context
              addTicket(newTicket);
            }}
            onCancel={() => {
              console.log("Cancel ticket submission");
            }}
          />
        </CardContent>
      </Card>
    </div>
  );
}