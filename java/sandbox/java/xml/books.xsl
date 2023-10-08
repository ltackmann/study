<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Programming Book Collection</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th align="left">Title</th>
                        <th align="left">Author</th>
                        <th align="left">Publication Date</th>
                    </tr>
                    <xsl:for-each select="books/book">
                        <tr>
                            <td>
                                <xsl:value-of select="title"/>
                            </td>
                            <td>
                                <xsl:for-each select="authors">
                                    <xsl:value-of select="author"/>
                                    <br></br>
                                </xsl:for-each>
                            </td>
                            <td>
                                <xsl:value-of select="pubDate"/>
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>