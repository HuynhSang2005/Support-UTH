package vn.id.tozydev.uthsupport.backend.controllers;

final class ApiPaths {
  static final String PREFIX = "/api/v1/";
  static final String USERS = PREFIX + "users";
  static final String USERNAME_PARAM = "{username}";
  static final String TICKETS = PREFIX + "tickets";
  static final String TICKET_ID_PARAM = "{ticketId}";
  static final String TICKET_STATUS = TICKET_ID_PARAM + "/status";
  static final String AUTH = PREFIX + "auth";
  static final String REGISTER = "register";
  static final String LOGIN = "login";
  static final String CATEGORIES = "categories";
  static final String CATEGORY_ID_PARAM = "{categoryId}";
}
