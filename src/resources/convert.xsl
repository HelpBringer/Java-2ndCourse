<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <html>
            <body>
                <h2>My Student Collection</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Surname</th>
                        <th>Name</th>
                        <th>Mark</th>
                        <th>Height</th>
                    </tr>
                    <xsl:for-each select="students_list/student">
                        <tr>
                            <td><xsl:value-of select="surname"/></td>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="mark"/></td>
                            <td><xsl:value-of select="height"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>