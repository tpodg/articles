package com.tpodg.hexagonal.framework;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostManagementRestAdapterTest {

    @Test
    @Order(1)
    void createPost_success() {
        given()
                .when()
                .header("content-type", "application/json")
                .body("""
                        {
                            "title": "Test title",
                            "content": "Test content..."
                        }
                        """)
                .post("/posts")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", is("Test title"))
                .body("content", is("Test content..."));
    }

    @Test
    @Order(2)
    void retrievePost_success() {
        given().when()
                .header("content-type", "application/json")
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("id", is(1))
                .body("title", is("Test title"))
                .body("content", is("Test content..."));
    }

    @Test
    @Order(2)
    void commentOnPost_success() {
        given().when()
                .header("content-type", "application/json")
                .body("""
                        {
                            "content": "Great post!"
                        }
                        """)
                .post("/posts/1/comment")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("content", is("Great post!"))
                .body("postId", is(1));
    }

    @Test
    @Order(3)
    void listPostComments_success() {
        given().when()
                .header("content-type", "application/json")
                .get("/posts/1/comments")
                .then()
                .statusCode(200)
                .log().all()
                .body("content[0]", is("Great post!"))
                .body("postId[0]", is(1));
    }
}