package com.haulmont.demo.imap.entity;

import com.haulmont.addon.imap.entity.ImapMessage;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.OnDeleteInverse;
import com.haulmont.cuba.core.global.DeletePolicy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "TASKTRACK_TASK_MESSAGE")
@Entity(name = "tasktrack_TaskMessage")
@Listeners({"tasktrack_TaskMessageListener"})
public class TaskMessage extends StandardEntity {
    @Column(name = "REPORTER")
    protected String reporter;

    @Column(name = "DIRECTION")
    protected String direction = MessageDirection.OUTBOX.getId();

    @NotNull
    @Column(name = "SUBJECT", nullable = false)
    protected String subject;

    @Lob
    @Column(name = "CONTENT")
    protected String content;

    @NotNull
    @OnDeleteInverse(DeletePolicy.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TASK_ID")
    protected Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAP_MESSAGE_ID")
    protected ImapMessage imapMessage;

    public MessageDirection getDirection() {
        return direction == null ? null : MessageDirection.fromId(direction);
    }

    public void setDirection(MessageDirection direction) {
        this.direction = direction == null ? null : direction.getId();
    }

    public ImapMessage getImapMessage() {
        return imapMessage;
    }

    public void setImapMessage(ImapMessage imapMessage) {
        this.imapMessage = imapMessage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }
}