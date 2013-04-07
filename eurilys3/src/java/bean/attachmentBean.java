
package bean;

public class attachmentBean {
    int attachment_id;
    String attachment_content;
    int attachment_task_id;

    public attachmentBean() {
    }

    public attachmentBean(int attachment_id, String attachment_content, int attachment_task_id) {
        this.attachment_id = attachment_id;
        this.attachment_content = attachment_content;
        this.attachment_task_id = attachment_task_id;
    }

    public int getAttachment_id() {
        return attachment_id;
    }

    public String getAttachment_content() {
        return attachment_content;
    }

    public int getAttachment_task_id() {
        return attachment_task_id;
    }

    public void setAttachment_id(int attachment_id) {
        this.attachment_id = attachment_id;
    }

    public void setAttachment_content(String attachment_content) {
        this.attachment_content = attachment_content;
    }

    public void setAttachment_task_id(int attachment_task_id) {
        this.attachment_task_id = attachment_task_id;
    }
}
