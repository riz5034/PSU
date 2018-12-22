'''
Project: Project Diamond
Team  Team 2
App 3 sftp
'''
import pysftp

class app3sftp:
    '''
    created the sftp for app3
    '''
    def sftp(self):
        '''
        The sftp recieving the payload file
        '''
        cnopts = pysftp.CnOpts()
        cnopts.hostkeys= None
        cinfo = {'cnopts':cnopts, 'host':'oz-ist-linux-fa18-411', 'username':'ftpuser', 'password':'test1234', 'port':103}
        try:
            with pysftp.Connection(**cinfo) as sftp:
                    print("Connection made")

                    print("Recieving payload file")
                    data = sftp.get("json.txt")
                    return True
        except:
                print("Log exception:",sys.exc_info()[0])
                raise
