package vn.id.tozydev.uthsupport.backend.controllers;

final class ApiPaths {
  static final String COMMENTS_ONLY = "comments";
  static final String REGISTER_ONLY = "register";
  static final String LOGIN_ONLY = "login";

  static final String USERNAME_PARAM = "{username}";
  static final String TICKET_ID_PARAM = "{ticketId}";
  static final String CATEGORY_ID_PARAM = "{categoryId}";
  static final String COMMENT_ID_PARAM = "{commentId}";

  static final String PREFIX = "/api/v1/";
  static final String USERS = PREFIX + "users";
  static final String TICKETS = PREFIX + "tickets";
  static final String TICKET_COMMENT = TICKETS + "/" + TICKET_ID_PARAM + "/" + COMMENTS_ONLY;
  static final String TICKET_STATUS = TICKET_ID_PARAM + "/status";
  static final String AUTH = PREFIX + "auth";
  static final String CATEGORIES = PREFIX + "categories";
  static final String CATEGORY_ASSIGNEES = CATEGORY_ID_PARAM + "/assignees";
  static final String COMMENTS = PREFIX + COMMENTS_ONLY;
}
