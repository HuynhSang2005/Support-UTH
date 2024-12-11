package vn.id.tozydev.uthsupport.backend.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CommentResponse;
import vn.id.tozydev.uthsupport.backend.models.dtos.comment.CreateCommentRequest;
import vn.id.tozydev.uthsupport.backend.models.entities.Comment;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {UserMapper.class, TicketMapper.class})
public interface CommentMapper {
  CommentResponse toResponse(Comment comment);

  Iterable<CommentResponse> toResponses(Iterable<Comment> comments);

  Comment toEntity(CreateCommentRequest request);
}
